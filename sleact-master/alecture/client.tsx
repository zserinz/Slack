import React from 'react';
import { render } from 'react-dom';
// import { BrowserRouter } from 'react-router-dom';
import App from '@layouts/App';

render(
//   <BrowserRouter>
    <App />
//   </BrowserRouter>,
  ,document.querySelector('#app'),
);

// 폴더 구조 (자유)
// ㄴ pages - 서비스 페이지
// ㄴ components - 짜잘 컴포넌트
// ㄴ layouts - 공통 레이아웃
