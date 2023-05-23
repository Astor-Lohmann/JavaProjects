import { Navigate, Route, Routes } from 'react-router-dom'
import Users from '../Pages/Users/Users'
import Login from '../Pages/Login/Login'
import Products from '../Pages/Products/Products'
import Sales from '../Pages/Sales/Sales'
import Layout from '../layout/layout'
import Home from '../Pages/Home/Home'
import { PrivateRoute } from './PrivateRoute'

export default function AppRoutes() {
  return (
    <>
      <Routes>
        <Route path={'/'} element={<Navigate replace to='/login' />} />
        <Route path={'/login'} element={<Login />} />

        <Route element={<PrivateRoute />}>
          <Route
            path={'/home'}
            element={
              <Layout>
                <Home />
              </Layout>
            }
          />

          <Route
            path={'/users'}
            element={
              <Layout>
                <Users />
              </Layout>
            }
          />
          <Route
            path={'/products'}
            element={
              <Layout>
                <Products />
              </Layout>
            }
          />
          <Route
            path={'/sales'}
            element={
              <Layout>
                <Sales />
              </Layout>
            }
          />
        </Route>
      </Routes>
    </>
  )
}