import { Navigate, Outlet } from 'react-router-dom'

export function PrivateRoute() {

const loginResponse = JSON.stringify(localStorage.getItem("loginStatus"))

  return loginResponse.includes("true") ? <Outlet /> : <Navigate to='/login' />
}