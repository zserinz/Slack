import React, { FC } from "react";
import loadable from "@loadable/component";
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";

const LogIn = loadable(() => import("@pages/Login"));
const SignUp = loadable(() => import("@pages/SignUp"));
const Workspace = loadable(() => import("@layouts/Workspace"));

const App: FC = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LogIn />} />
        <Route path="/login" element={<LogIn />} />
        <Route path="/signup" element={<SignUp />} />
        <Route path="/workspace/:workspace" Component={Workspace} />
      </Routes>
    </Router>
  );
};

export default App;
