import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import Users from './Users';
import * as userServices from '../../services/userServices'
import { ToastContainer } from 'react-toastify';
import { BrowserRouter } from 'react-router-dom';
describe('Teste da tela User', () => {

    test('renders page with correct titles', () => {
        render(<Users />);
        expect(screen.getByText('Cadastro de Novo Usuário')).toBeInTheDocument();
        expect(screen.getByText('Lista de Usuários')).toBeInTheDocument();
    });

    it('Deve exibir uma mensagem de erro na tela', async () => {
        jest.spyOn(userServices, 'getRegisteredUsers')
            .mockRejectedValue(new Error())

        render(
            <>
                <Users />
                <ToastContainer />
            </>, { wrapper: BrowserRouter })

        await waitFor(() => {
            expect(screen.getByText('Erro ao desconhecido ao buscar usuários'))
                .toBeInTheDocument()
        })

    })

}) 