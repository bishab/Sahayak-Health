import React, { useEffect, useState } from "react";
import axios from "axios";
import { scaleQuantile } from "d3-scale";
import ReactTooltip from "react-tooltip";
import { memo } from "react";
import { ComposableMap, Geographies, Geography } from "react-simple-maps";
import { Card } from "@mantine/core";

const geoUrl =
  "https://raw.githubusercontent.com/zcreativelabs/react-simple-maps/master/topojson-maps/world-110m.json";

const MapChart = ({ setTooltipContent, type = "cases" }) => {
  const [data, setData] = useState([]);
  const [content, setContent] = useState("");
  const [min, setMin] = useState(0);
  const [max, setMax] = useState(0);

  const colorScale = scaleQuantile().domain([min, max]).range([
    // "#fff5f0",
    "#fee0d2",
    // "#fcbba1",
    "#fc9272",
    "#fb6a4a",
    "#ef3b2c",
    "#cb181d",
    "#a50f15",
    "#67000d",
  ]);

  // find minimum of array.cases
  const minCases = (data) => {
    let min = data[0][type];
    data.forEach((d) => {
      if (d[type] < min) {
        min = d[type];
      }
    });
    return min;
  };
  const maxCases = (data) => {
    let max = data[0][type];
    data.forEach((d) => {
      if (d[type] > max) {
        max = d[type];
        console.log(d);
      }
    });
    return max;
  };

  useEffect(() => {
    axios(`https://corona.lmao.ninja/v2/countries`).then((data) => {
      setData(data.data);
      setMax(maxCases(data.data));
      setMin(minCases(data.data));
      console.log({ min, max, data: data.data });
    });
  }, []);

  return (
    <div
      style={{
        display: "flex",
        alignItems: "center",
        justifyContent: "center",
      }}
    >
      <div style={{ width: 800, margin: "auto" }}>
        <Card shadow="sm" padding="lg">
          <ComposableMap data-tip="" projectionConfig={{ scale: 170 }}>
            <Geographies geography={geoUrl}>
              {({ geographies }) =>
                geographies.map((geo) => {
                  const d = data.find(
                    (s) => s.countryInfo.iso3 === geo.properties.ISO_A3
                  );
                  return (
                    <Geography
                      key={geo.rsmKey}
                      geography={geo}
                      fill={d ? colorScale(d[type]) : "#F5F4F6"}
                      onClick={null}
                      style={{
                        pressed: {
                          fill: d ? colorScale(d[type]) : "#F5F4F6",
                          stroke: "none",
                        },
                      }}
                      onMouseEnter={() => {
                        const { NAME, POP_EST } = geo.properties;
                        setContent(`${NAME} — ${d[type]}`);
                        console.log({ min, max });
                      }}
                      onMouseLeave={() => {
                        setContent("");
                      }}
                    />
                  );
                })
              }
            </Geographies>
          </ComposableMap>
          <ReactTooltip>{content}</ReactTooltip>
        </Card>
      </div>
    </div>
  );
};

export default memo(MapChart);
