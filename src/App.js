import { MantineProvider, ColorSchemeProvider } from "@mantine/core";
import { useLocalStorageValue } from "@mantine/hooks";
import { React } from "react";
import AppBody from "./components/AppBody";
import { NotificationsProvider } from "@mantine/notifications";
import { Chatroom } from "@scalableminds/chatroom";
import { ModalsProvider } from "@mantine/modals";

function App() {
  const [colorScheme, setColorScheme] = useLocalStorageValue({
    key: "color-scheme",
    defaultValue: "dark",
  });
  const toggleColorScheme = (value) =>
    setColorScheme(value || (colorScheme === "dark" ? "light" : "dark"));
  return (
    <ColorSchemeProvider
      colorScheme={colorScheme}
      toggleColorScheme={toggleColorScheme}
    >
      <MantineProvider
        theme={{ colorScheme, fontFamily: "Poppins" }}
        withGlobalStyles
      >
        <NotificationsProvider>
          <ModalsProvider>
            <AppBody />
            <Chatroom />
          </ModalsProvider>
        </NotificationsProvider>
      </MantineProvider>
    </ColorSchemeProvider>
  );
}

export default App;
