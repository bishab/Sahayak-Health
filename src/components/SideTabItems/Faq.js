import { React, useEffect, useState } from "react";
import { Accordion } from "@mantine/core";
import axios from "axios";
import { Title, Space, useMantineTheme, ScrollArea } from "@mantine/core";
import Parser from 'html-react-parser';


function Faq() {
  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get("https://nyc-cto.github.io/coronavirus-answers/answers.json")
      .then((response) => {
        const res = response.data;
        setData(res);
        console.log(res);
      })
      .catch((error) => console.log(error));
  }, []);

  const arr = data.map((data) => {
    return (
      <Accordion>
        <Accordion.Item label={data.title}>{Parser(data.content)}</Accordion.Item>
      </Accordion>
    );
  });

  return (
    <div>
      <ScrollArea
        style={{ height: "82vh", overflowX: "hidden" }}
        offsetScrollbars
      >
        <Title order={1}>Frequently Asked Questions About Covid-19</Title>
        <Space h="xl" />
        {arr}
      </ScrollArea>
    </div>
  );
}

export default Faq;
