import { BrowserRouter } from "react-router-dom";
import "bootstrap-icons/font/bootstrap-icons.css";
import { ToastContainer } from "react-toastify";
import "./App.css";
import AppRoutes from "./routes/AppRoutes";
import { LoginProvider } from "./contexts/loginContext";

export default function App() {
  return (
    
    <BrowserRouter>
      <LoginProvider>
        <AppRoutes />
        <ToastContainer />
      </LoginProvider>
    </BrowserRouter>

  );
}
