import React from "react";
import { ActionIcon, Title, useMantineColorScheme } from "@mantine/core";
import { SunIcon, MoonIcon } from "@modulz/radix-icons";

const HeaderContent = () => {
  const { colorScheme, toggleColorScheme } = useMantineColorScheme();
  const dark = colorScheme === "dark";
  return (
    <div style={{ display: "flex", flexDirection: "row" }}>
      <Title order={3} style={{ flex: 1 }}>
        Bigyan
      </Title>

      <ActionIcon
        variant="outline"
        color={dark ? "yellow" : "blue"}
        onClick={() => toggleColorScheme()}
        title="Toggle color scheme"
      >
        {dark ? (
          <SunIcon style={{ width: 18, height: 18 }} />
        ) : (
          <MoonIcon style={{ width: 18, height: 18 }} />
        )}
      </ActionIcon>
    </div>
  );
};

export default HeaderContent;
