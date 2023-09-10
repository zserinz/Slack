import React, {
  Component,
  FC,
  PropsWithChildren,
  VFC,
  useCallback,
  useState,
} from "react";
import fetcher from "@utils/fetcher";
import useSWR from "swr";
import axios from "axios";
import { Navigate, Routes, useParams } from "react-router";
import { Link, Route } from "react-router-dom";
import loadable from "@loadable/component";
import gravatar from "gravatar";
import { toast } from "react-toastify";

import {
  Header,
  RightMenu,
  ProfileImg,
  WorkspaceWrapper,
  Workspaces,
  Channels,
  Chats,
  WorkspaceName,
  MenuScroll,
  ProfileModal,
  LogOutButton,
  WorkspaceButton,
  AddButton,
  WorkspaceModal,
} from "@layouts/Workspace/styles";
import Menu from "@components/Menu";
import { IChannel, IUser } from "@typings/db";
import { Button, Input, Label } from "@pages/SignUp/styles";
import useInput from "@hooks/useInput";
import Modal from "@components/Modal";
import CreateChannelModal from "@components/CreateChannelModal";
import InviteWorkspaceModal from "@components/InviteWorkspaceModal";
import InviteChannelModal from "@components/InviteChannelModal";

const Channel = loadable(() => import("@pages/Channel"));
const DirectMessage = loadable(() => import("@pages/DirectMessage"));

const Workspace: VFC = () => {
  const [showUserMenu, setShowUserMenu] = useState(false);
  const [showWorkspaceModal, setShowWorkspaceModal] = useState(false);
  const [showCreateWorkspaceModal, setShowCreateWorkspaceModal] =
    useState(false);
  const [showInviteWorkspaceModal, setShowInviteWorkspaceModal] =
    useState(false);
  const [showInviteChannelModal, setShowInviteChannelModal] = useState(false);
  const [showCreateChannelModal, setShowCreateChannelModal] = useState(false);
  const [newWorkspace, onChangeNewWorkspace, setNewWorkpsace] = useInput("");
  const [newUrl, onChangeNewUrl, setNewUrl] = useInput("");

  const { workspace, channel } = useParams<{
    workspace: string;
    channel: string;
  }>();
  const {
    data: UserData,
    error,
    isValidating,
    mutate,
  } = useSWR<IUser>("http://localhost:3095/api/users", fetcher, {
    dedupingInterval: 3000,
  });
  const { data: ChannelData, mutate: mutateChannel } = useSWR<IChannel[]>(
    UserData
      ? `http://localhost:3095/api/workspaces/${workspace}/chennels`
      : null,
    fetcher
  );

  const onLogout = useCallback(() => {
    axios
      .post("/api/users/logout", null, {
        withCredentials: true,
      })
      .then(() => {
        mutate(false, false);
      });
  }, []);

  const onCloseModal = useCallback(() => {
    setShowCreateWorkspaceModal(false);
    setShowCreateChannelModal(false);
    setShowInviteWorkspaceModal(false);
    setShowInviteChannelModal(false);
  }, []);

  const onClickUserProfile = useCallback(
    (e: { stopPropagation: () => void }) => {
      e.stopPropagation();
      setShowUserMenu((prev) => !prev);
    },
    []
  );

  const onClickCreateWorkspace = useCallback(() => {
    setShowCreateWorkspaceModal(true);
  }, []);

  const onCreateWorkspace = useCallback(
    (e: any) => {
      e.preventDefault();
      e.preventDefault();
      if (!newWorkspace || !newWorkspace.trim()) return;
      if (!newUrl || !newUrl.trim()) return;
      axios
        .post(
          "/api/workspaces",
          {
            workspace: newWorkspace,
            url: newUrl,
          },
          {
            withCredentials: true,
          }
        )
        .then(() => {
          revalidate();
          setShowCreateWorkspaceModal(false);
          setNewWorkpsace("");
          setNewUrl("");
        })
        .catch((error) => {
          toast.error(error.response?.data, { position: "bottom-center" });
        });
    },
    [newWorkspace, newUrl]
  );

  const toggleWorkspaceModal = useCallback(() => {
    setShowWorkspaceModal((prev) => !prev);
  }, []);

  const onClickAddChannel = useCallback(() => {
    setShowCreateChannelModal(true);
  }, []);

  const onClickInviteWorkspace = useCallback(() => {
    setShowInviteWorkspaceModal(true);
  }, []);

  if (!UserData) {
    return <Navigate to="/login" />;
  }

  return (
    <div>
      <Header>
        <RightMenu>
          <span onClick={onClickUserProfile}>
            <ProfileImg
              src={gravatar.url(UserData.email, { s: "28px", d: "retro" })}
              alt={UserData.nickname}
            />
            {showUserMenu && (
              <Menu
                style={{ right: 0, top: 38 }}
                show={showUserMenu}
                onCloseModal={onClickUserProfile}
              >
                <ProfileModal>
                  <img
                    src={gravatar.url(UserData.email, {
                      s: "36px",
                      d: "retro",
                    })}
                    alt={UserData.nickname}
                  />
                  <div>
                    <span id="profile-name">{UserData.nickname}</span>
                    <span id="profile-active">Active</span>
                  </div>
                </ProfileModal>
                <LogOutButton onClick={onLogout}>로그아웃</LogOutButton>
              </Menu>
            )}
          </span>
        </RightMenu>
      </Header>
      <WorkspaceWrapper>
        <Workspaces>
          {UserData?.Workspaces.map((ws) => {
            return (
              <Link key={ws.id} to={`/workspace/${123}/channel/일반`}>
                <WorkspaceButton>
                  {ws.name.slice(0, 1).toUpperCase()}
                </WorkspaceButton>
              </Link>
            );
          })}
          <AddButton onClick={onClickCreateWorkspace}>+</AddButton>
        </Workspaces>
        <Channels>
          <WorkspaceName onClick={toggleWorkspaceModal}>Sleact</WorkspaceName>
          <MenuScroll>
            <Menu
              show={showWorkspaceModal}
              onCloseModal={toggleWorkspaceModal}
              style={{ top: 95, left: 80 }}
            >
              <WorkspaceModal>
                <h2>Sleact</h2>
                <button onClick={onClickInviteWorkspace}>
                  워크스페이스에 사용자 초대
                </button>
                <button onClick={onClickAddChannel}>채널 만들기</button>
                <button onClick={onLogout}>로그아웃</button>
              </WorkspaceModal>
            </Menu>
          </MenuScroll>
        </Channels>
        <Chats>
          <Routes>
            <Route
              path="/workspace/:workspace/channel/:channel"
              element={<Channel />}
            />
            <Route
              path="/workspace/:workspace/dm/:id"
              element={<DirectMessage />}
            />
          </Routes>
        </Chats>
      </WorkspaceWrapper>
      <Modal show={showCreateWorkspaceModal} onCloseModal={onCloseModal}>
        <form onSubmit={onCreateWorkspace}>
          <Label id="workspace-label">
            <span>워크스페이스 이름</span>
            <Input
              id="workspace"
              value={newWorkspace}
              onChange={onChangeNewWorkspace}
            />
          </Label>
          <Label id="workspace-url-label">
            <span>워크스페이스 url</span>
            <Input id="workspace" value={newUrl} onChange={onChangeNewUrl} />
          </Label>
          <Button type="submit">생성하기</Button>
        </form>
      </Modal>
      <CreateChannelModal
        show={showCreateChannelModal}
        onCloseModal={onCloseModal}
        setShowCreateChannelModal={setShowCreateChannelModal}
      />
      <InviteWorkspaceModal
        show={showInviteWorkspaceModal}
        onCloseModal={onCloseModal}
        setShowInviteWorkspaceModal={setShowInviteWorkspaceModal}
      />
      <InviteChannelModal
        show={showInviteChannelModal}
        onCloseModal={onCloseModal}
        setShowInviteChannelModal={setShowInviteChannelModal}
      />
    </div>
  );
};

export default Workspace;
function revalidate() {
  throw new Error("Function not implemented.");
}
