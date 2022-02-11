import { MantineProvider, ColorSchemeProvider, Navbar } from "@mantine/core";
import { React, useState } from "react";
import Chatbot from "./components/Chatbot";
import Navbarx from "./components/Navbarx";

function App() {
  const [colorScheme, setColorScheme] = useState("light");
  const toggleColorScheme = (value) =>
    setColorScheme(value || (colorScheme === "dark" ? "light" : "dark"));
  return (
    <ColorSchemeProvider
      colorScheme={colorScheme}
      toggleColorScheme={toggleColorScheme}
    >
      <MantineProvider theme={{ colorScheme }} withGlobalStyles>
        <Navbarx />
        <Chatbot />
      </MantineProvider>
    </ColorSchemeProvider>
  );
}

export default App;
