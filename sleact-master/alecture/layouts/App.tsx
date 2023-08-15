import React, { FC } from "react"
import loadable from '@loadable/component'
import { Route, Routes, BrowserRouter as Router } from "react-router-dom";

const LogIn = loadable(()=> import('@pages/Login'));
const SignUp = loadable(()=> import('@pages/SignUp'));

const App: FC = () => {
    return (
    <Router>
        <Routes> 
            <Route path="/" element={<LogIn />} />
            <Route path="/login" element={<LogIn />} />
            <Route path="/signup" element={<SignUp />} />
        </Routes>
    </Router>
    );
};

export default App;
