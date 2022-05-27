import React from "react";
import {
  Card,
  Avatar,
  Paper,
  Badge,
  Image,
  Text,
  SimpleGrid,
  Space,
  Title,
  ScrollArea,
  Group,
  useMantineTheme,
  Container,
} from "@mantine/core";
import AppointmentList from "./AppointmentList";
import ProfileImage from "./ProfileImage";
import UserDetails from "./UserDetails";
import BmiCalculator from "./BmiCalculator";
function MainProfile() {
  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];
  return (
    <div style={{ margin: "auto" }}>
      <ScrollArea
        style={{ height: "82vh", overflowX: "hidden" }}
        offsetScrollbars
      >
        <Container size={"xl"}>
          <SimpleGrid cols={2} breakpoints={[{ maxWidth: 'sm', cols: 1 }]}>
            <UserDetails />
            <AppointmentList />
            <BmiCalculator />
          

            {/* </SimpleGrid>
              <SimpleGrid cols={1}> */}
          </SimpleGrid>
        </Container>
      </ScrollArea>
    </div>
  );
}

export default MainProfile;
