import "./layout.css";
import "bootstrap/dist/css/bootstrap.min.css";

import { useNavigate, useLocation } from "react-router-dom";
import { LoginContext } from "../contexts/loginContext";
import { useContext } from 'react';
import PharmLab from "./../images/PharmLab.jpg"

export default function Layout({ children }) {
  const { logout } = useContext(LoginContext);
  const { navigate } = useNavigate();

  const location = useLocation()

  const sidebar = [{
    header: "Vendas",
    icon: "bi bi-bag-check",
    route: "/sales"

  }, {
    header: "Produtos",
    icon: "bi bi-cart2",
    route: "/products"
  }, {
    header: "Usuários",
    icon: "bi bi-person-workspace",
    route: "/users"
  }]

  const handleLogoutClick = async () => {
    await logout();
    navigate("/login");
  }


  return (
    <>
      <div style={{ display: "flex", flexDirection: "row", height: "100vh" }}>
        <nav>
          <div className="title" style={{textAlign:"center", marginTop:"10px"}}>
             <a a className="navbar-brand" href="/home"><img src={PharmLab} style={{maxWidth:"130px", maxHeight:"130px", width:"auto", height:"auto"}}/></a>
            <button className="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
              <span className="navbar-toggler-icon"></span>
            </button>
          </div>
          <div className="navBarMenu">
            <ul className="ulMenu">
              <li className={location.pathname === "/sales" ? "navigateMenuSellected" : "navigateMenu"} >
                <i id="icone" className="bi bi-bag-check"></i>
                <a className="nav-link " href="/sales">Vendas</a>
              </li>
              <li className={location.pathname === "/products" ? "navigateMenuSellected" : "navigateMenu"}>
                <i id="icone" className="bi bi-cart2"></i>
                <a className="nav-link" href="/products">Produtos</a>
              </li>
              <li className={location.pathname === "/users" ? "navigateMenuSellected" : "navigateMenu"}>
                <i id="icone" className="bi bi-person-workspace"></i>
                <a className="nav-link" href="/users">Usuários</a>
              </li>
            </ul>
            <div className="sair">
              <div className="navigateMenuSair">
                <i id="icone" className="bi bi-door-open"></i>
                <a className="nav-link" href="/" onClick={handleLogoutClick}>Sair</a>
              </div>
            </div>
          </div>
        </nav>
        <div className="children" >
          {children}
        </div>
      </div>
    </>
  )
}