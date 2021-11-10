import axios from 'axios';
import { AxiosResponse } from 'axios';

const user = JSON.parse(localStorage.getItem('user'));
let token = (user && user.token) && user.token;

axios.defaults.baseURL = "http://localhost:8080/api";
axios.defaults.headers = {
  'Access-Control-Allow-Origin': '*',
  'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
  'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
}
if (token)  {
  axios.defaults.headers = {
    'Authorization': 'Bearer ' + token, 
    'Access-Control-Allow-Origin': '*',
    'Access-Control-Allow-Methods': 'GET,PUT,POST,DELETE,PATCH,OPTIONS',
    'Access-Control-Allow-Headers': 'Origin, Content-Type, X-Auth-Token'
  }
}


const responseBody = response => response.data;
const requests = {
  get: url => axios.get(url).then(responseBody),
  post: (url, body) => axios.post(url, body).then(responseBody),
  put: (url, body) => axios.put(url, body).then(responseBody),
  del: url => axios.delete(url).then(responseBody),
};
const activities = {
  auth: (activity, body) => requests.post(`/auth/${activity}`, body),
  list: (activity, target) => requests.get(`/${activity}-management/${target}`),
  search: (activity, target, keyword) => requests.get(`/${activity}-management/${target}/${keyword}`),
  details: (activity,  target, id) => requests.get(`/${activity}-management/${target}/${id}`),
  details_type: (activity,  target, field, id) => requests.get(`/${activity}-management/${target}/${field}/${id}`),
  create: (activity, body) => requests.post(`/${activity}-management`, body),
  update: activity => requests.put(`/activities/${activity.id}`, activity),
  delete: id => requests.del(`/activities/${id}`),
};

export default activities;