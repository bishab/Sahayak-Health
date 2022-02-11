import React, { useState, useEffect } from "react";

import { Widget, addResponseMessage, setQuickButtons } from "react-chat-widget";
import "react-chat-widget/lib/styles.css";
import "./Chatbot.css";
function Chatbot() {
  // const buttons = [
  //   { label: "Call your nearest Hospital", value: "1" },
  //   { label: "Seek Consultation", value: "2" },
  // ];

  useEffect(() => {
    addResponseMessage("Ask us anything about COVID-19 ");
    //

    // setQuickButtons(buttons);
  }, []);

  const handleNewUserMessage = (newMessage) => {
    console.log(`New message incoming! ${newMessage}`);
    // Now send the message throught the backend API
    let response = "I am getting your msg";
    addResponseMessage(response);
  };
  return (
    <div className="App">
      <Widget
        title={"Bigyan's covid help line"}
        subtitle={"Wassap Brothers"}
        handleNewUserMessage={(message) => handleNewUserMessage(message)}
        emojis="yes"
      />
    </div>
  );
}
export default Chatbot;
