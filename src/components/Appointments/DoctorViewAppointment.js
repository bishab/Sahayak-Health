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

function DoctorViewAppointment() {
    const theme = useMantineTheme();

    const secondaryColor = theme.colorScheme === 'dark'
      ? theme.colors.dark[1]
      : theme.colors.gray[7];

      const [data, setData] = useState([]);


  //Linking API
  // useEffect(() => {
  //   axios
  //     .get(
  //       `http://20.41.221.66:7000/patient/getreg/}`
  //     )
  //     .then((response) => {
  //       const res = response.data;
  //       console.log(res);
        
  //     })
  //     .catch((error) => console.log(error));
  // }, []);

  // const arr = data.map((data) => {
  //   return (
  //     <Grid.Col span={4}>
  //       <div style={{ width: 340, margin: "auto" }}>
  //         <Card shadow="sm" padding="lg">
            

  //           <Group
  //             position="apart"
  //             style={{ marginBottom: 5, marginTop: theme.spacing.sm }}
  //           >
  //             <Text
  //               style={{
  //                 color: secondaryColor,
  //                 lineHeight: 1.5,
  //                 minHeight: 48,
  //                 maxHeight: 48,
  //                 overflow: "clip",
  //               }}
  //               weight={500}
  //             >
  //               {data.first_name}
  //             </Text>
  //             <Badge color="pink" variant="light">
  //               {data.gender}
  //             </Badge>
  //           </Group>

  //           <Text
  //             size="sm"
  //             style={{
  //               color: secondaryColor,
  //               lineHeight: 1.5,
  //               minHeight: 100,
  //               maxHeight: 100,
  //               overflow: "clip",
  //             }}
  //           >
  //             {data.email}
  //           </Text>

  //           {/* <Button
  //             variant="light"
  //             color="blue"
  //             fullWidth
  //             style={{ marginTop: 14 }}
  //             component="a"
  //             target="_blank"
  //             rel="noopener noreferrer"
  //             href={data.url}
  //           >
  //             Full News
  //           </Button> */}
  //         </Card>
  //       </div>
  //     </Grid.Col>
  //   );
  // });
  return (
    <div>
      <ScrollArea
        style={{ height: "82vh", overflowX: "hidden" }}
        offsetScrollbars
      >
        <Grid gutter="sm">

          <h1>
            hey
          </h1>
        </Grid>
      </ScrollArea>

      <Center>
        {/* <Pagination
          style={{ marginTop: "15px" }}
          total={total}
          onChange={(page) => {
            setPage(page);
          }}
        /> */}
      </Center>
    </div>
  );
  
}

export default DoctorViewAppointment