import axios from 'axios';
import { AxiosResponse } from 'axios';

axios.defaults.baseURL = "http://localhost:8080/api";

const responseBody = response => response.data;
const requests = {
  get: url => axios.get(url).then(responseBody),
  post: (url, body) => axios.post(url, body).then(responseBody),
  put: (url, body) => axios.put(url, body).then(responseBody),
  del: url => axios.delete(url).then(responseBody),
};
const activities = {
  list: (activity, target) => requests.get(`/${activity}-management/${target}`),
  search: (activity, target, keyword) => requests.get(`/${activity}-management/${target}/${keyword}`),
  details: (activity,  target, id) => requests.get(`/${activity}-management/${target}/${id}`),
  details_type: (activity,  target, field, id) => requests.get(`/${activity}-management/${target}/${field}/${id}`),
  create: activity => requests.post(`/${activity}-management`),
  update: activity => requests.put(`/activities/${activity.id}`, activity),
  delete: id => requests.del(`/activities/${id}`),
};

export default activities;