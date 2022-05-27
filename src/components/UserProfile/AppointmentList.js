import { React, useEffect, useState } from "react";
import axios from "axios";
import {
  Card,
  Paper,
  Badge,
  Image,
  Title,
  Text,
  Grid,
  Space,
  Button,
  Group,
  useMantineTheme,
  Container,
} from "@mantine/core";
function AppointmentList() {
  let userEmail = localStorage.getItem("userEmail");
  console.log("Appointment EMail", userEmail);
  const [data, setData] = useState([]);

  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];

  useEffect(() => {
    axios
      .get(`http://20.41.221.66:7000/patient/getapp/${userEmail}/`)
      .then((response) => {
        const res = response.data;
        console.log("Appoi", res);
        setData(res);
      })
      .catch((error) => console.log(error));
  }, [userEmail]);

  const arr = data.map((data) => {
    return (
      <Card
        shadow="sm"
        padding="lg"
        style={{ marginBottom: theme.spacing.sm, display: "flex" }}
      >
        <div style={{ marginBottom: 5, marginTop: theme.spacing.sm, flex: 1 }}>
          <Title order={2}>{data.hospital}</Title>

          <Title order={4} style={{ color: secondaryColor, lineHeight: 1.8 }}>
            {data.department}
          </Title>

          <Text size="sm" style={{ color: secondaryColor, lineHeight: 1.5 }}>
            {data.time}
          </Text>
        </div>
        <Badge color="green" variant="light">
          Accepted
        </Badge>
      </Card>
    );
  });

  return (
    <>
      <Paper padding="md" shadow="xs" radius="lg" withBorder>
        <Title align="center" order={2}>
          My Appointments
        </Title>
        <Space h="md" />

        <Container>
          {arr}

          <Space h="md" />
        </Container>
      </Paper>
    </>
  );
}

export default AppointmentList;
