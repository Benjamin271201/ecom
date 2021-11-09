import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import 'semantic-ui-css/semantic.min.css';
import "bootstrap/dist/css/bootstrap.min.css";  
import { createBrowserHistory } from "history";
import {BrowserRouter} from 'react-router-dom';

export const customHistory = createBrowserHistory();

ReactDOM.render(
  <BrowserRouter history={customHistory}>
    <React.StrictMode>
      <App /> 
    </React.StrictMode>
  </BrowserRouter>,
  document.getElementById('root')
);

