import React, { FC, PropsWithChildren, useCallback } from "react";
import fetcher from '@utils/fetcher';
import useSWR from 'swr';
import axios from "axios";
import { Navigate } from "react-router";

const Workspace = ({ children }:PropsWithChildren) => {
    const { data, error, isValidating, mutate } = useSWR('http://localhost:3095/api/users', fetcher)

    const onLogout = useCallback(() => {
        axios.post('/api/users/logout', null, {
            withCredentials: true,
        })
        .then(()=> {
            mutate('/api/users')
        })
    },[])

    if (!data) {
        return <Navigate to="/login" />
    }

    return (
        <div>
            <button onClick={onLogout}>로그아웃</button>
            {children}
        </div>
    );
};

export default Workspace;
