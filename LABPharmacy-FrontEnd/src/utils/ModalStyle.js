import styled from "styled-components";


export const ModalStyle = styled.div`
    .modal-overlay {
    z-index: 9999;
    width: 100vw;
    height: 100vh;
    position: fixed;
    top: 0;
    bottom: 0;
    right: 0;
    left: 0;
    background: rgba(0, 0, 0, 0.7);         
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .modal-box {
    display: flex;
    flex-direction:column;
    margin: 0 auto;
    height: 93%;
    width: 70%;
    gap: 4em;
    column-gap: 1%;
    background: white;
    position: fixed;
    top: 1.5em;
    padding: 3em;
    border-radius: 0.5em;
    align-items: right;
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
        background: white;
        border-radius: 9px;
        
        :hover {
            background: grey;
        }
    }
    >div{
      display:flex;
      flex-direction:row;
      gap:2em;
      >input{
        border-radius:0.5em;
      }
    }
    
  }
  
  `