/* eslint-disable jsx-a11y/alt-text */
import { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";

import PharmLab from "./../../images/PharmLab.jpg"
import * as React from "react";
import { toast } from "react-toastify";
import { CssVarsProvider } from "@mui/joy/styles";
import Button from "@mui/joy/Button";
import Sheet from "@mui/joy/Sheet";
import Typography from "@mui/joy/Typography";
import FormControl from "@mui/joy/FormControl";
import FormLabel from "@mui/joy/FormLabel";
import Input from "@mui/joy/Input";
import { useNavigate } from "react-router-dom";
import { LoginContext } from "../../contexts/loginContext";
import { useContext, useEffect } from "react";
import {
  DarkAndLightMode,
} from "../../functions/loginFormFunctions";
import { callApi } from "../../functions/loginFormFunctions";

<DarkAndLightMode />;

export default function FormularioLogin() {
  const navigate = useNavigate();

  const { login } = useContext(LoginContext);

  const [form, setForm] = useState({
    email: "",
    password: "",
  });

  
  async function handleLoginClick(e) {
    e.preventDefault();
    if (form.email === "" || form.password === "") {
      toast.error("Preencha os campos de e-mail e senha corretamente.");
    } else {
      await callApi(form).then((response) => {
        const userType = JSON.parse(localStorage.getItem("userData")).data.data.type;
        if (
          (response.data.status === 200 || response.data.status === 201)
          &&
          userType === "ADMIN") {
          login();
          navigate("/home");
        } else {
          toast.error("Usuário não tem permissão de acesso.");
        }
      });
    }
  }

  return (
    <CssVarsProvider >
      <main style={{  height:"100%", flexDirection:"column", display:"flex", alignItems: "center", marginTop:"8%" }}>
        <DarkAndLightMode />
        <Sheet
          sx={{
            width: 300,
            mx: "auto", // margin left & right
            my: 4, // margin top & botom
            py: 3, // padding top & bottom
            px: 2, // padding left & right
            display: "flex",
            flexDirection: "column",
            gap: 2,
            borderRadius: "sm",
            boxShadow: "md",
          }}
          variant="outlined"
        >
          <img src={PharmLab} style={{alignSelf:"center" ,maxWidth:"100px", maxHeight:"100px", width:"auto", height:"auto"}}/>
          <div>
            <Typography level="h4" component="h1" textAlign={"center"}>
              <b>Login!</b>
            </Typography>
            <Typography level="body2" textAlign={"center"}>
              Insira seus dados para continuar.
            </Typography>
          </div>
          <FormControl>
            <FormLabel>E-mail</FormLabel>
            <Input
              // html input attribute
              name="email"
              type="email"
              placeholder="johndoe@email.com"
              required
              value={form.email}
              onChange={(e) =>
                setForm((prev) => ({ ...prev, email: e.target.value }))
              }
            />
          </FormControl>
          <FormControl>
            <FormLabel>Senha</FormLabel>
            <Input
              // html input attribute
              name="password"
              type="password"
              placeholder="password"
              required
              value={form.password}
              onChange={(e) =>
                setForm((prev) => ({ ...prev, password: e.target.value }))
              }
            />
          </FormControl>

          <Button color="success" onClick={handleLoginClick}>
            Acessar
          </Button>

          <Typography
            // endDecorator={<Link href="/sign-up">Sign up</Link>}
            fontSize="sm"
            sx={{ textAlign: "center" }}
          >
            Não tem uma conta ainda? <br />
            Peça a um Administrador para criar uma para você.
          </Typography>
        </Sheet>
      </main>
    </CssVarsProvider>
  );
}
