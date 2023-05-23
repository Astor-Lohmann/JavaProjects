import React, { useState, useEffect } from "react";
import "./Home.css";

export default function Home() {
  const userData = JSON.parse(localStorage.getItem("userData"));

  const userName = userData.data.data.fullName.split(" ")[0];

  const [motivation, setMotivation] = useState("");

  useEffect(() => {
    const sentences = [
      '"Acredite em si mesmo e todo o resto cairá no lugar. Tenha fé em seus próprios poderes, incluindo sua capacidade de se recuperar de qualquer coisa." - Neil Patel',
      '"Não importa o quão devagar você vá, desde que você não pare." - Confúcio',
      '"O sucesso não é final, o fracasso não é fatal: é a coragem de continuar que conta." - Winston Churchill',
      '"Não existe um caminho para a felicidade. A felicidade é o caminho." - Mahatma Gandhi',
      '"A única maneira de fazer um bom trabalho é amar o que você faz." - Steve Jobs',
      '"Você não pode mudar o vento, mas pode ajustar as velas do barco para chegar onde quer." - Confúcio',
      '"O sucesso é a soma de pequenos esforços repetidos dia após dia." - Robert Collier',
      '"Não deixe suas limitações ditarem quem você é, deixe sua determinação e força de vontade fazê-lo." - Christine Eberle',
      '"Não desista do que você mais quer na vida. O tempo pode ser difícil, mas o que é seu sempre encontrará um caminho." - Autor desconhecido',
      '"Lembre-se: tudo que você enfrenta, toda condenação e crítica, é uma oportunidade para aprender e crescer. Todos esses obstáculos são apenas degraus para o sucesso." - Zig Ziglar.',
    ];

    const randomSentence =
      sentences[Math.floor(Math.random() * sentences.length)];

    setMotivation(randomSentence);
  }, []);

  const currentHour = new Date().getHours();

  let greeting;
  if (currentHour < 12) {
    greeting = `Bom dia, ${userName}!`;
  } else if (currentHour < 18) {
    greeting = `Boa tarde, ${userName}!`;
  } else {
    greeting = `Boa noite, ${userName}!`;
  }

  return (
    <>
      {userName !== '' &&
        <div className="body">
          <h1>{greeting}</h1>
          <p>{motivation}</p>
        </div>
      }
    </>
  );
}
