import { green } from "@mui/material/colors"
import { arrowLeft, arrowRight } from "../../utils/Svgs"

export default function({numPage, setNumpage, itens, itensForpage }){

    if ((numPage)>(Math.ceil(itens.length/itensForpage))){
        setNumpage(1)
    }

    return(
        <div className="numpage" style={{justifyContent: "center"}}>
            <button onClick={()=>{
            if(numPage>1){
              setNumpage(numPage - 1)
            }
            }}>
                {arrowLeft}
            </button>
            <p>Página {numPage} / {Math.ceil(itens.length/itensForpage)}</p>
            <button onClick={()=>{
                if((itens.length/(itensForpage*numPage)) > 1 ){
                    setNumpage(numPage + 1)
                }
              
            }}>
                {arrowRight}
            </button>
        </div>
    )
}