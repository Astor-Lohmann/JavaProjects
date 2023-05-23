import React from "react";
import Home from "./Home";
import { render, screen } from "@testing-library/react";

describe("Home component", () => {
  beforeEach(() => {
    const userData = {
      data: {
        data: {
          fullName: "John Doe",
        },
      },
    };
    localStorage.setItem("userData", JSON.stringify(userData));
  });

  afterEach(() => {
    localStorage.removeItem("userData");
  });

  it("should render the greeting with the user's name correctly", () => {
    render(<Home />);
    const greeting = screen.getByText(/(.*), john!/i);
    expect(greeting).toBeInTheDocument();
  });

  it("should render a motivational quote", () => {
    render(<Home />);
    const quote = screen.getByText(/"(.*)"/i);
    expect(quote).toBeInTheDocument();
  });

  it("should render the greeting with the user's name correctly based on the time of day", () => {
    const mockDateMorning = new Date("2023-04-22T09:00:00");
    jest.spyOn(global, "Date").mockImplementation(() => mockDateMorning);
    render(<Home />);
    let greeting = screen.queryByText(/bom dia, john!/i);
    expect(greeting).toBeInTheDocument();
    global.Date.mockRestore();
  
    const mockDateAfternoon = new Date("2023-04-22T14:00:00");
    jest.spyOn(global, "Date").mockImplementation(() => mockDateAfternoon);
    render(<Home />);
    greeting = screen.queryByText(/boa tarde, john!/i);
    expect(greeting).toBeInTheDocument();
    global.Date.mockRestore();
  
    const mockDateNight = new Date("2023-04-22T19:00:00");
    jest.spyOn(global, "Date").mockImplementation(() => mockDateNight);
    render(<Home />);
    greeting = screen.queryByText(/boa noite, john!/i);
    expect(greeting).toBeInTheDocument();
    global.Date.mockRestore();
  });
});
