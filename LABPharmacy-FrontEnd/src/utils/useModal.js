import { useState } from "react";

export default function useModal() {
  const [isOpen, setisOpen] = useState(false);

  const toggle = () => {
    setisOpen(!isOpen);
  };
  const close = () => {
    setisOpen(false);
  };

  

  return {
    isOpen,
    toggle,
    close
  };
}
export { useModal}