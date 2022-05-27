import { React, useEffect, useState } from "react";
import {
  Card,
  Image,
  Text,
  Badge,
  Button,
  Grid,
  Group,
  Center,
  Pagination,
  ScrollArea,
  useMantineTheme,
} from "@mantine/core";
import axios from "axios";

function News() {
  const theme = useMantineTheme();
  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];
  const [data, setData] = useState([]);
  const [total, setTotal] = useState(0);
  const [page, setPage] = useState(1);
  const NEWS_PER_PAGE = 9;

  //Linking API
  useEffect(() => {
    axios
      .get(
        `https://api.coronatracker.com/news/trending?countryCode=NP&country=Nepal&language=en&limit=${NEWS_PER_PAGE}&offset=${
          (page - 1) * NEWS_PER_PAGE + 1
        }`)
      .then((response) => {
        const res = response.data;
        console.log(res.total);
        setTotal(Math.ceil(res.total / NEWS_PER_PAGE));
        setData(res.items);
        console.log(res);
      })
      .catch((error) => console.log(error));
  }, [page]);

  const arr = data.map((data) => {
    return (
      <Grid.Col span={4}>
        <div style={{ width: 340, margin: "auto" }}>
          <Card shadow="sm" padding="lg">
            <Card.Section>
              {/* Image URL */}
              <Image src={data.urlToImage} height={160} />
            </Card.Section>

            <Group
              position="apart"
              style={{ marginBottom: 5, marginTop: theme.spacing.sm }}
            >
              <Text
                style={{
                  color: secondaryColor,
                  lineHeight: 1.5,
                  minHeight: 48,
                  maxHeight: 48,
                  overflow: "clip",
                }}
                weight={500}
              >
                {data.title}
              </Text>
              <Badge color="pink" variant="light">
                {data.siteName}
              </Badge>
            </Group>

            <Text
              size="sm"
              style={{
                color: secondaryColor,
                lineHeight: 1.5,
                minHeight: 100,
                maxHeight: 100,
                overflow: "clip",
              }}
            >
              {data.description}
            </Text>

            <Button
              variant="light"
              color="blue"
              fullWidth
              style={{ marginTop: 14 }}
              component="a"
              target="_blank"
              rel="noopener noreferrer"
              href={data.url}
            >
              Full News
            </Button>
          </Card>
        </div>
      </Grid.Col>
    );
  });
  return (
    <div>
      <ScrollArea
        style={{ height: "82vh", overflowX: "hidden" }}
        offsetScrollbars
      >
        <Grid gutter="sm">{arr}</Grid>
      </ScrollArea>

      <Center>
        <Pagination
          style={{ marginTop: "15px" }}
          total={total}
          onChange={(page) => {
            setPage(page);
          }}
        />
      </Center>
    </div>
  );
}

export default News;
