import { useColorScheme } from "@mui/joy";
import { useState, useEffect, useContext } from "react";
import Button from "@mui/joy/Button";
import api from "../api/api";
import { toast } from "react-toastify";

export function DarkAndLightMode() {
  const { mode, setMode } = useColorScheme();
  const [mounted, setMounted] = useState(false);

  useEffect(() => {
    setMounted(true);
  }, []);
  if (!mounted) {
    return null;
  }

  return (
    <div style={{ textAlign: "center", padding: "10px" }}>
      <Button
        variant="outlined"
        onClick={() => {
          setMode(mode === "light" ? "dark" : "light");
        }}
      >
        {mode === "light" ? "Turn dark" : "Turn light"}
      </Button>
    </div>
  );
}

export async function callApi(form) {
  try {
    const response = await api
      .post(
        "/usuario/login",
        {
          email: form.email,
          password: form.password,
        },
        {
          headers: {
            "Content-Type": "application/json",
          },
        }
      )
    localStorage.setItem("userId", JSON.stringify(response.data.data));
    await getUserById(response.data.data)
    return response;
  } catch (err) {
    toast.error(err.response.data.message);
  }
}

export async function getUserById(id) {
  try {
    const response = await api.get("/usuario/" + id, id, {
      headers: {
        "Content-Type": "application/json",
      },
    });
    localStorage.setItem("userData", JSON.stringify(response));
    return response;
  } catch (err) {
    console.error(err);
  }
}
