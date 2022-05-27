import React, { useState, useEffect } from "react";
import { useMantineColorScheme } from "@mantine/core";
import { Widget, addResponseMessage, setQuickButtons } from "react-chat-widget";
import "react-chat-widget/lib/styles.css";

import axios from "axios";

const rename = original => original.map((obj)=> {
                    obj['value'] = obj['payload']; // Assign new key
                    delete obj['payload']; // Delete old key
                    obj['label'] = obj['title']; // Assign new key
                    delete obj['title']; // Delete old key
                    return obj;
                });
const Chatbot = () => {
  const { colorScheme, toggleColorScheme } = useMantineColorScheme();
  const dark = colorScheme === "dark";
  const [buttonsarray, setButtons] = useState([]);

  // const buttonsarray2 = [
  //   { label: "Yes", value: "Yes" },
  //   { label: "No", value: "No" },
  // ];


  useEffect(() => {
    addResponseMessage("Hello");

    //
  }, [1]);

  // const [data, setData] = useState([]);
  const handleNewUserMessage = async (newMessage, buttons, data) => {
    console.log(`New message incoming! ${newMessage}`);
    console.log(`New message incoming! ${buttons}`);

    // Now send the message throught the backend API
    const message = {
      "sender_id": "Bigyanic",

      "message": `${newMessage}` || `${buttons}`,
    };
    const res = await axios.post(
      "http://20.41.221.66:5005/webhooks/rest/webhook",
      message
    );
    console.log({res:res.data});
    res.data.forEach((item) => {
      console.log({item})
      addResponseMessage(item.text);
      if(item?.buttons){
        console.log("has buttonss")
        const buttonsArr = item.buttons.map((obj)=> {
          obj['value'] = obj['payload']; // Assign new key
          delete obj['payload']; // Delete old key
          obj['label'] = obj['title']; // Assign new key
          delete obj['title']; // Delete old key
          return obj;
        });
        console.log({buttonsArr})
         setQuickButtons(buttonsArr);
      } else {
        setQuickButtons([]);
      }
    });
  };

  return (
    <div className="App">
      <Widget
        title={"Chat With Sahayak"}
        subtitle={"AI Powered Health Bot"}
        handleNewUserMessage={(message) => {console.log(message);
          handleNewUserMessage(message)}}
        emojis={false}
        showTimeStamp={false}
        handleQuickButtonClicked = {(value) => {
          console.log(value);
          handleNewUserMessage(value)
        }}
        // resizable={true}
      />
    </div>
  );
};

export default Chatbot;
