import { useEffect } from "react";
import { ModalStyle } from "./ModalStyle";

export default function Modal({children, isOpen, toggle, close}) {
    useEffect(() => {
        const handleKeyDown = (event) => {
          if (event.key === "Escape") {
            if(isOpen){
                close();

            }
          }
        }
    
        document.addEventListener("keydown", handleKeyDown);
    
        return () => {
          document.removeEventListener("keydown", handleKeyDown);
        };
      }, [isOpen, toggle]);

    return (
        <ModalStyle>
            {isOpen && (
                <div className="modal-overlay" onClick={toggle} style={{zIndex:13}}>
                    <div onClick={(e) => e.stopPropagation()}  className="modal-box">
                        {children}
                    </div>
                </div>
            )}
        </ModalStyle>
    );
}