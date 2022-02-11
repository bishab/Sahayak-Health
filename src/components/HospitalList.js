import { React, useEffect, useState } from "react";
import { Table, useMantineTheme } from "@mantine/core";
import axios from "axios";
function HospitalList() {
  const theme = useMantineTheme();

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get("https://corona.askbhunte.com/api/v1/hospitals")
      .then((response) => {
        const res = response.data;
        setData(res.data);
        console.log(res);
      })
      .catch((error) => console.log(error));
  }, []);

  const arr = data.map((data) => {
    return (
      <tr key={data.name}>
        <td>{data.name}</td>
        <td>{data.address}</td>
        <td>{data.phone}</td>
        <td>{data.contact_person}</td>
        <td>{data.contact_person_number}</td>
        <td>{data.capacity.beds}</td>
        <td>{data.capacity.ventilators}</td>
      </tr>
    );
  });
  return (
    <Table>
      <thead>
        <tr>
          <th>Name</th>
          <th>Address</th>
          <th>Phone</th>
          <th>Contact Person</th>
          <th>Conttact Person Number</th>
          <th>Beds</th>
          <th>Ventilators</th>
        </tr>
      </thead>
      <tbody>{arr}</tbody>
    </Table>
  );
}

export default HospitalList;
