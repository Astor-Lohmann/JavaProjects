import styled from 'styled-components';

export const List = styled.div`
    background:#F1EDED; 
    background:white;
    border-radius:16px; 
    display:flex;
    height:auto;
    padding:1%;
    flex-direction: column;
    display:flex;
    flex-direction: column;
    overflow-y:auto;
    overflow-x:auto;
    ::-webkit-scrollbar {
        width: 0.7em;
        height: 0.7em;
    }
    ::-webkit-scrollbar-track {
        background: transparent;
    }
    ::-webkit-scrollbar-thumb {
        background: #d3d3d3;
        border-radius: 9px;
        
        :hover {
            background: grey;
        }
    }
    
    >h1{
        display:flex;
        justify-content:center;
    }
    >div{
        width:150%;
        border-radius:0.3em;
        display:flex;
        padding-top:0.4em;
        padding-right:0.8em;
        flex-direction:row;
        align-items:center;
        
        >p{
            border-radius:0.5em;
            padding:1em;
            margin:0;
            align-items:center;
            
        }
        .description{
            background:#D9D9D9;
            border-radius:0.5em;
            padding:1em;
            margin:0;
            align-items:center;
            height:3.5em;
            overflow:hidden;

        }
        .clicavel{
            background:#D9D9D9;
            border-radius:0.5em;
            padding:1em;
            margin:0;
            align-items:center;
            :hover{
                background:grey;
                cursor:pointer;
            }

        }
        .highlight {
            background-color: #F8D06A;
        }
        >span{
            background:#D9D9D9;
            border-radius:0.5em;
            padding:1em;
            margin:0;
            align-items:center;
        }
    }
`