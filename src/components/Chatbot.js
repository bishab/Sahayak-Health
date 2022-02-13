import React, { useState, useEffect } from "react";

import { Widget, addResponseMessage, setQuickButtons } from "react-chat-widget";
import "react-chat-widget/lib/styles.css";
import axios from "axios";
const Chatbot = () => {
  useEffect(() => {
    addResponseMessage("Ask us anything about COVID-19 ");
    //
  }, []);

  // const [data, setData] = useState([]);
  const handleNewUserMessage = async (newMessage) => {
    console.log(`New message incoming! ${newMessage}`);
    // Now send the message throught the backend API
    const message = {
      message: `${newMessage}`,
    };
    const res = await axios.post(
      "http://13.71.80.137:5005/webhooks/rest/webhook",
      message
    );
    addResponseMessage(res.data[0].text);
  };

  return (
    <div className="App">
      <Widget
        title={"Bigyan's covid help line"}
        subtitle={"Wassap bros"}
        handleNewUserMessage={(message) => handleNewUserMessage(message)}
        emojis="yes"
      />
    </div>
  );
};

export default Chatbot;
