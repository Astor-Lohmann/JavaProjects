import "bootstrap/dist/css/bootstrap.min.css";
import FormularioLogin from "../../Components/Forms/LoginForm";
import { DefaltLayout } from "../../Components/MainStyle/DafaultLayout";

export default function Login() {

  return (
    <div style={{height:"100vh"}}>
      <DefaltLayout>
        <FormularioLogin />
      </DefaltLayout>
    </div>
  );
}
