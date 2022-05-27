import { React, useEffect, useState } from "react";
import { Avatar } from "@mantine/core";
import axios from "axios";
import { Title, Space, useMantineTheme, ScrollArea, Table, Center, Pagination } from "@mantine/core";

function DoctorList() {
  const theme = useMantineTheme();
  const [total, setTotal] = useState(0);
  const [page, setPage] = useState(1);
  const DOCS_PER_PAGE = 30;

  const secondaryColor =
    theme.colorScheme === "dark" ? theme.colors.dark[1] : theme.colors.gray[7];
  const [data, setData] = useState([]);

  useEffect(() => {
    axios
      .get("https://bigyanic.github.io/assets/Doctors.json")
      .then((response) => {
        const res = response.data;
        setData(res);
        setTotal(Math.ceil(res.length/DOCS_PER_PAGE))
        console.log(res);
      })
      .catch((error) => console.log(error));
  }, []);

  const arr = data.slice((page-1)*DOCS_PER_PAGE,page*DOCS_PER_PAGE).map((data) => {
    return (
      <tr key={data.id}>
        <td><Avatar src={data.image}></Avatar></td>
        <td>{data.name}</td>
        <td>{data.description}</td>
        <td>{data.hospitals?.map((h)=> h.name).join(', ')}</td>
        {/* <td>{data.phone}</td> */}
        {/* <td><a href={data.website}>{data.hospital_name}</a></std> */}
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
              {/* <th>Phone</th> */}
              <th>Hospital Name</th>
            </tr>
          </thead>
          <tbody>{arr}</tbody>
        </Table>
        <Center>
        <Pagination
          style={{ marginTop: "15px" }}
          total={total}
          onChange={(page) => {
            console.log({page})
            setPage(+page);
          }}
        />
      </Center>
      </ScrollArea>
    </div>
  );
}

export default DoctorList;
