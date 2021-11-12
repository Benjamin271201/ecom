import { createBrowserHistory } from "history";
import api from "../api/api";

export const customHistory = createBrowserHistory();

class AuthService {
  login(username, password) {
    return api
      .auth("login",{
        username,
        password
      })
      .then(response => {
        console.log(response)
        if (response.token) {
          localStorage.setItem("user", JSON.stringify(response));
        }

        return response;
      });
  }

  logout() {
    localStorage.removeItem("user");
    customHistory.push("/")
    window.location.reload();
  }

  register(username, password, firstName, lastName, email,phone) {
    return api.auth("register", {
        username,
        password,
        firstName, 
        lastName,
        email,
        phone
    })
    .then(response => {
      if (response.token) {
        localStorage.setItem("user", JSON.stringify(response));
      }
      return response;
    })    
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}

export default new AuthService();