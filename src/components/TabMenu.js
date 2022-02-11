import { useState } from "react";
import { Tabs, Menu, Switch, Button } from "@mantine/core";
import {
  BackpackIcon,
  CameraIcon,
  HomeIcon,
  ImageIcon,
  PersonIcon,
  RocketIcon,
} from "@modulz/radix-icons";
import { ActionIcon, useMantineColorScheme } from "@mantine/core";
import { SunIcon, MoonIcon } from "@modulz/radix-icons";

function TabMenu() {
  const { colorScheme, toggleColorScheme } = useMantineColorScheme();
  const dark = colorScheme === "dark";
  return (
    <>
      <Tabs position="right">
        <Tabs.Tab label="Homepage" icon={<HomeIcon />}>
          Gallery tab content
        </Tabs.Tab>
        <Tabs.Tab label="Data Visualization" icon={<CameraIcon />}>
          Messages tab content
        </Tabs.Tab>
        <Tabs.Tab label="About Project" icon={<BackpackIcon />}>
          Settings tab content
        </Tabs.Tab>
        <Tabs.Tab label="Emergency Services " icon={<RocketIcon />} color="red">
          Settings tab content
        </Tabs.Tab>
      </Tabs>
      <RocketIcon />
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
    </>
  );
}
export default TabMenu;
