import { React, useEffect, useState } from "react";
import { Table, useMantineTheme, ScrollArea } from "@mantine/core";
import axios from "axios";
function HospitalList() {
  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get("https://bigyanic.github.io/assets/Hospitals2.json")
      .then((response) => {
        const res = response.data;
        setData(res);
        console.log(res);
      })
      .catch((error) => console.log(error));
  }, []);

  const arr = data.map((data) => {
    return (
      <tr key={data.id}>
        <td>{data.name}</td>
        <td>{data.location}</td>
        <td>{data.phone}</td>
        <td><a href={data.website}>{data.website}</a></td>
        <td>{data.description}</td>
        {/* <td>{data.contact_person_number}</td>
        <td>{data.capacity.beds}</td>
        <td>{data.capacity.ventilators}</td>  */}
      </tr>
    );
  });
  return (
    <>
      <ScrollArea
        style={{ height: "82vh", overflowX: "hidden" }}
        offsetScrollbars
      >
        <Table>
          <thead>
            <tr>
              <th>Name</th>
              <th>Address</th>
              <th>Phone</th>
              <th>Website</th>
              <th>Description</th>
            </tr>
          </thead>
          <tbody>{arr}</tbody>
        </Table>
      </ScrollArea>
    </>
  );
}

export default HospitalList;
