import React, { Component, FC, PropsWithChildren, useCallback } from "react";
import fetcher from "@utils/fetcher";
import useSWR from "swr";
import axios from "axios";
import { Navigate, Routes } from "react-router";
import { Route } from "react-router-dom";
import loadable from "@loadable/component";
import gravatar from "gravatar";

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
} from "@layouts/Workspace/styles";

const Channel = loadable(() => import("@pages/Channel"));
const DirectMessage = loadable(() => import("@pages/DirectMessage"));

const Workspace = ({ children }: PropsWithChildren) => {
  const { data, error, isValidating, mutate } = useSWR(
    "http://localhost:3095/api/users",
    fetcher,
    {
      dedupingInterval: 3000,
    }
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

  if (!data) {
    return <Navigate to="/login" />;
  }

  return (
    <div>
      <Header>
        <RightMenu>
          <span>
            <ProfileImg
              src={gravatar.url(data.email, { s: "28px", d: "retro" })}
              alt={data.nickname}
            />
          </span>
        </RightMenu>
      </Header>
      <button onClick={onLogout}>로그아웃</button>
      <WorkspaceWrapper>
        <Workspaces>test</Workspaces>
        <Channels>
          <WorkspaceName>Sleact</WorkspaceName>
          <MenuScroll>MenuScroll</MenuScroll>
        </Channels>
        <Chats>
          <Routes>
            <Route
              path="/workspace/:workspace/channel/:channel"
              Component={Channel}
            />
            <Route
              path="/workspace/:workspace/dm/:id"
              Component={DirectMessage}
            />
          </Routes>
        </Chats>
      </WorkspaceWrapper>
    </div>
  );
};

export default Workspace;
