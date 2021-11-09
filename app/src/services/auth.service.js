import axios from "axios";

const API_URL = "http://localhost:8080/api/auth/";

class AuthService {
  login(username, password) {
    return axios
      .post(API_URL + "login", {
        username,
        password
      })
      .then(response => {
        console.log(response)
        if (response.data.token) {
          localStorage.setItem("user", JSON.stringify(response.data));
        }

        return response.data;
      });
  }

  logout() {
    localStorage.removeItem("user");
    window.location.reload();
  }

  register(firstname, lastname, username, email, password, phone) {
    return axios.post(API_URL + "register", {
        firstname, 
        lastname,
        username,
        password,
        email,
        phone
    });
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('user'));;
  }
}

export default new AuthService();