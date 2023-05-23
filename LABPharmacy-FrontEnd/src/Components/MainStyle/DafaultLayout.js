import React from 'react';

import styled from 'styled-components';

export const DefaltLayout = styled.div`
    background: #F1EDED;
    width:100%;
    height:150%;
    padding-left:2em;
    padding-right:2em;
    padding-bottom:2em;

    @media({width:"1000px"}){
        height:180%;
        width:120%
    }

    gap:1em;
    display:flex;
    flex-direction: column;
    overflow-x:hidden;
    ::-webkit-scrollbar {
        width: 0.3em;
        height: 0.3em;
    }
    ::-webkit-scrollbar-track {
        background: transparent;
    }
    ::-webkit-scrollbar-thumb {
        background: white;
        border-radius: 9px;
        
        :hover {
            background: grey;
        }
    }
    >h1{
        display:flex;
        justify-content:center;
    }
    .numpage{
        justify-content:center;
        display:flex;
        >button{
            width:2em;
            border:none;
        }
        >p{
            border-radius:0.5em;
            padding:1em;
            margin:0;
            align-items:center;
        }
    }
    
`