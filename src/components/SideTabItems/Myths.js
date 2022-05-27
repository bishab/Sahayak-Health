import { React, useEffect, useState } from "react";
import { Avatar } from "@mantine/core";
import axios from "axios";
import { Title, Space, useMantineTheme, ScrollArea, Table } from "@mantine/core";

function Faq() {
  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get("https://bigyanic.github.io/assets/Doctors.json")
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
        <td><Avatar src={data.image}></Avatar></td>
        <td>{data.name}</td>
        <td>{data.description}</td>
        {/* <td>{data.phone}</td> */}
        {/* <td><a href={data.website}>{data.hospital_name}</a></td> */}
        {/* <td>{data.contact_person_number}</td>
        <td>{data.capacity.beds}</td>
        <td>{data.capacity.ventilators}</td>  */}
      </tr>
    );
  });

  return (
    <div>
      <ScrollArea
        style={{ height: "82vh", overflowX: "hidden" }}
        offsetScrollbars
      >
        <Table>
          <thead>
            <tr>
              <th></th>
              <th>Name</th>
              <th>Description</th>
              <th>Phone</th>
              <th>Hospital Name</th>
            </tr>
          </thead>
          <tbody>{arr}</tbody>
        </Table>
      </ScrollArea>
    </div>
  );
}

export default Faq;
