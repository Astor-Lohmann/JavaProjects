import { createContext, useState } from 'react';
import { toast } from "react-toastify";

export const LoginContext = createContext();

export const LoginProvider = ({ children }) => {
  const [loginData, setLoginData] = useState(false);

  const login = () => {
      const loginStatus = { loginData: true};
      localStorage.setItem("loginStatus", JSON.stringify(loginStatus))
      const userData = JSON.parse(localStorage.getItem("userData"));
      setLoginData(loginStatus);
      toast.success(`Olá, ${userData.data.data.fullName.split(" ")[0]}.`);
  };

  const logout = () => {
    const loginStatus = { loginData: false};
    localStorage.setItem("loginStatus", JSON.stringify(loginStatus))
    localStorage.setItem("userData", JSON.stringify({}))
    setLoginData(loginStatus);
    toast.info("Saindo do sistema!");
  };

  return (
    <LoginContext.Provider value={{ loginData, login, logout }}>
      {children}
    </LoginContext.Provider>
  );
};