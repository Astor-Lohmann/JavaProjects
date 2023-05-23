import { render, screen } from '@testing-library/react';
import '@testing-library/jest-dom/extend-expect';
import FormTest from './SalesForm';


describe('testar formulário de vendas', () => {
    test('Deve retornar se o botão registrar aparece no formulário', () => {
        render(<FormTest />);
        const button = screen.getByRole('button', { name: /Registrar/i });
        expect(button).toBeInTheDocument();
    });
    test('Deve retornar se o botão limpar aparece no formulário', () => {
        render(<FormTest />);
        const button = screen.getByRole('button', { name: /Limpar/i });
        expect(button).toBeInTheDocument();
    });
    
    test('se renderiza os inputs do formulário', () => {
        render(<FormTest />);

        screen.queryAllByText(/Vendedor/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Produto/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Preço Unitário/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Quantidade/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Forma de Pagamento/i).map(item=>{
            expect(item).toBeInTheDocument();
        })
        screen.queryAllByText(/Valor Total/i).map(item=>{
            expect(item).toBeInTheDocument();
        })

    });
});