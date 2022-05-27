import { useState, useEffect } from 'react';
import {
  Card,
  Blockquote,
  ScrollArea,
  Badge,
  Button,
  Group,
  Paper,
 
  Space,
  useMantineTheme,
} from "@mantine/core";
import Mobileapp from "./Mobileapp";
import TopName from "./TopName";
import WhySahayak from "./WhySahayak";

function Homepage() {
  const theme = useMantineTheme();
 

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];

  return (
    <>
      <ScrollArea
        style={{ height: "82vh", overflowX: "hidden" }}
        offsetScrollbars
      >
       
        <div
          style={{
            display: "flex",
            flexDirection: "row",
          }}
        >
          <div style={{ margin: "auto" }}>
            <Card shadow="sm" padding="xl">
              <TopName />
              <Space h="xl" />
              <Paper padding="sm" shadow="xs">
                <Blockquote cite="– Harry J. Johnson">
                  The human body has been designed to resist an infinite number
                  of changes and attacks brought about by its environment. The
                  secret of good health lies in successful adjustment to
                  changing stresses on the body.
                </Blockquote>
              </Paper>
              <Space h="xl" />

              <WhySahayak />
              <Space h="xl" />
              <Mobileapp/>
            </Card>
          </div>
        </div>
      </ScrollArea>
    </>
  );
}

export default Homepage;
