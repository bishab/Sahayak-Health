import React from "react";
import {
  Card,
  Grid,
  Button,
  Group,
  Text,
  Space,
  Title,
  useMantineTheme,
  Container,
  Paper,
} from "@mantine/core";
import WhySahayak from "./WhySahayak";
import MobileAppLogo from "./MobileAppLogo";

function Mobileapp() {
  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];

function DownloadNow() {
  window.open("https://cdn-142.anonfiles.com/V96db8k4y6/4daaec67-1653563335/SahayakHealth_v1.1.apk");
  
}

  return (
    <div style={{ margin: "auto" }}>
      <Paper>
        <Container>
        <Space h="md" />

        <Group>
        
            <MobileAppLogo/>
            <Title order={2}> Sahayak Health Mobile Application</Title>
            <Space w="md" />

            <Button onClick={DownloadNow}>Download Now!</Button>
       
        </Group>
        <Space h="md" />

        </Container>
      </Paper>
    </div>
  );
}

export default Mobileapp;
