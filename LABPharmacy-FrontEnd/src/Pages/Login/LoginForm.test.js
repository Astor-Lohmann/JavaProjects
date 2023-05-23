import { render, screen, fireEvent, waitFor } from "@testing-library/react";
import userEvent from "@testing-library/user-event";
import { LoginContext } from "../../contexts/loginContext";
import FormularioLogin from "../../Components/Forms/LoginForm";
import { toast } from "react-toastify";
import { callApi } from "../../functions/loginFormFunctions";

// Mock the LoginContext value
jest.mock("../../contexts/loginContext", () => ({
  LoginContext: {
    Consumer: (props) => props.children({ login: jest.fn() }),
  },
}));

// Mock the callApi function
jest.mock("../../functions/loginFormFunctions", () => ({
  callApi: jest.fn(),
}));

describe("FormularioLogin", () => {
  it("should render the login form correctly", () => {
    render(<FormularioLogin />);

    // Check if the email and password fields are present
    expect(screen.getByLabelText("E-mail")).toBeInTheDocument();
    expect(screen.getByLabelText("Senha")).toBeInTheDocument();

    // Check if the "Acessar" button is present
    expect(screen.getByRole("button", { name: "Acessar" })).toBeInTheDocument();

    // Check if the "Não tem uma conta ainda?" text is present
    expect(screen.getByText("Não tem uma conta ainda?")).toBeInTheDocument();
  });

  it("should show an error message when the email or password fields are empty", async () => {
    render(<FormularioLogin />);

    // Click the "Acessar" button without filling in the email and password fields
    fireEvent.click(screen.getByRole("button", { name: "Acessar" }));

    // Check if the error message is displayed
    await waitFor(() => expect(toast.error).toHaveBeenCalled());
    expect(toast.error).toHaveBeenCalledWith(
      "Preencha os campos de e-mail e senha corretamente."
    );
  });

  it("should show an error message when the user doesn't have permission to access", async () => {
    // Mock the callApi function to return an error response
    callApi.mockResolvedValueOnce({ data: { status: 401 } });

    render(
      <LoginContext.Consumer>
        {({ login }) => <FormularioLogin />}
      </LoginContext.Consumer>
    );

    userEvent.type(screen.getByLabelText("E-mail"), "john@doe.com");
    userEvent.type(screen.getByLabelText("Senha"), "password");

    fireEvent.click(screen.getByRole("button", { name: "Acessar" }));

    // Wait for the toast to appear
    await waitFor(() => {
      expect(callApi).toHaveBeenCalledWith({
        email: "john@doe.com",
        password: "password",
      });
      screen.expect(toast.error).toHaveBeenCalledWith(
        "Usuário não tem permissão de acesso."
      );
    });

    it("should redirect to the home page and call the login function when the user is an admin", async () => {
      // Mock the callApi function to return a successful response and set the userType
      localStorage.setItem(
        "userData",
        JSON.stringify({ data: { data: { type: "ADMIN" } } })
      );
      callApi.mockResolvedValue({ data: { status: 200 } });

      render(
        <LoginContext.Consumer>
          {({ login }) => <FormularioLogin />}
        </LoginContext.Consumer>
      );

      userEvent.type(screen.getByLabelText("E-mail"), "john@doe.com");
      userEvent.type(screen.getByLabelText("Senha"), "password");

      fireEvent.click(screen.getByRole("button", { name: "Acessar" }));

      // Wait for the redirect and login to occur
      await waitFor(() => {
        expect(callApi).toHaveBeenCalledWith({
          email: "john@doe.com",
          password: "password",
        });
        screen.expect(login).toHaveBeenCalled();
        screen.expect(screen.queryByText("Login!")).not.toBeInTheDocument();
        screen
          .expect(screen.getByText("Bem-vindo à Home Page!"))
          .toBeInTheDocument();
      });
    });
  });

  // reset the window location after each test
  afterEach(() => {
    window.location.href = "http://localhost/";
  });
});
