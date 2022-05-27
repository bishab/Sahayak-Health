import {
  MantineProvider,
  ColorSchemeProvider,
  Modal,
  Text,
} from "@mantine/core";
import { useLocalStorageValue } from "@mantine/hooks";
import { React, useState, useEffect } from "react";
import Chatbot from "./components/Chatbot/Chatbot";
import Routes from "./components/Routes";
import { NotificationsProvider } from "@mantine/notifications";

import { ModalsProvider } from "@mantine/modals";

function App() {
  const [colorScheme, setColorScheme] = useLocalStorageValue({
    key: "color-scheme",
    defaultValue: "dark",
  });
  const [opened, setOpened] = useState(false);

  useEffect(() => {
    setOpened(true);
  }, []);

  const toggleColorScheme = (value) =>
    setColorScheme(value || (colorScheme === "dark" ? "light" : "dark"));
  return (
    <ColorSchemeProvider
      colorScheme={colorScheme}
      toggleColorScheme={toggleColorScheme}
    >
      <MantineProvider theme={{ colorScheme }} withGlobalStyles>
        <NotificationsProvider>
          <ModalsProvider>
            <Modal
              opened={opened}
              size={700}
              padding={"md"}
              onClose={() => setOpened(false)}
              title="Important Information"
            >
              {/* Modal content */}
              <Text>
                {`A new contagious virus of Monkeypox has been spreading over 15 countries now. We urge you all to inform via this notice some of the crucial informations regarding this virus.
Its symptoms include:`}
              </Text>
              <Text>
                - Fever
                </Text> 
              <Text>-Headache</Text> 
              <Text>- Muscle Ache</Text>
              <Text>- Back Ache</Text> 
              <Text>- Swollen Lymph Nodes</Text>
              <Text>- Chill & Exhaustion</Text>
              
              <Text>
                {`In order to stay away from the virus, here are the preventive measures:
- Isolate infected patients from others who could be at risk for infection.
- Practice good hand hygiene after contact`}
              </Text>
            </Modal>
            <Routes />
            <Chatbot />
          </ModalsProvider>
        </NotificationsProvider>
      </MantineProvider>
    </ColorSchemeProvider>
  );
}

export default App;
