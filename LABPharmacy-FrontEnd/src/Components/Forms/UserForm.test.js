import { render, screen,fireEvent, waitFor  } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import FormTest from './UserForm';


describe('testar formulário de cadastro', () => {
    test('Deve retornar se o botão cadastrar aparece no formulário', () => {
        render(<button>Cadastrar</button>);
        const button = screen.getByRole('button', { name: /Cadastrar/i });
        expect(button).toBeInTheDocument();
    });
    
    test('se renderiza os inputs do formulário', () => {
        render(<FormTest />);

        screen.queryAllByText(/Nome Completo/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/CPF/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Data De Nascimento/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/E-mail/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Escolha uma Senha para o Usuário/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Tipo do Usuário/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/CEP/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Estado/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Cidade/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Bairro/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Logradouro/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Número/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Complemento/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
    });
    it('Deve mostrar a validação das mensagens de erro quando o campo não é preenchido', async () => {
        const setUpdateList = jest.fn();
        render(<FormTest setUpdateList={setUpdateList} />);
    
        fireEvent.submit(screen.getAllByRole('button')[0]);
    
        expect(await screen.findByText('Preencha o Nome Completo!')).toBeInTheDocument();
        expect(await screen.findByText('Preencha um CPF válido!')).toBeInTheDocument();
        expect(await screen.findByText('Preencha a Data de Nascimento!')).toBeInTheDocument();
        expect(await screen.findByText('Preencha o Email!')).toBeInTheDocument();
        expect(await screen.findByText('Preencha a senha!')).toBeInTheDocument();
    });

});

// npm test -- --coverage 