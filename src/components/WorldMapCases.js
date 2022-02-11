import React, { useEffect, useState } from "react";
import axios from "axios";
// // import { csv } from "d3-fetch";
import { scaleLinear } from "d3-scale";
// import {
//   ComposableMap,
//   Geographies,
//   Geography,
//   Sphere,
//   Graticule,
// } from "react-simple-maps";

import ReactTooltip from "react-tooltip";

// const geoUrl =
//   "https://raw.githubusercontent.com/zcreativelabs/react-simple-maps/master/topojson-maps/world-110m.json";

// const MapChart = () => {
//   const [data, setData] = useState([]);
//   const [content, setContent] = useState("");

//   useEffect(() => {
//     axios(`https://corona.lmao.ninja/v2/countries`).then((data) => {
//       setData(data.data);
//       console.log(data.data);
//     });
//   }, []);

//   return (
//     <React.Fragment>
//       <ComposableMap
//         projectionConfig={{
//           rotate: [0, 0, 0],
//           scale: 147,
//         }}
//       >
//         {data.length > 0 && (
//           <Geographies geography={geoUrl}>
//             {({ geographies }) =>
//               geographies.map((geo) => {
//                 console.log(geo.properties.ISO_A3);
//                 const d = data.find(
//                   (s) => s.countryInfo.iso3 === geo.properties.ISO_A3
//                 );
//                 console.log({ d });
//                 return (
//                   <Geography
//                     key={geo.rsmKey}
//                     geography={geo}
//
//                     onClick={() => {
//                       setContent(geo.properties.NAME);
//                     }}
//                   />
//                 );
//               })
//             }
//           </Geographies>
//         )}
//       </ComposableMap>
//       <ReactTooltip>{content}</ReactTooltip>
//     </React.Fragment>
//   );
// };

// export default MapChart;

import { memo } from "react";
import {
  ZoomableGroup,
  ComposableMap,
  Geographies,
  Geography,
} from "react-simple-maps";

const geoUrl =
  "https://raw.githubusercontent.com/zcreativelabs/react-simple-maps/master/topojson-maps/world-110m.json";

const colorScale = scaleLinear()
  .domain([10000, 10000000])
  .range(["#ffedea", "#ff5233"]);

const WorldMapCases = ({ setTooltipContent }) => {
  const [data, setData] = useState([]);
  const [content, setContent] = useState("");

  useEffect(() => {
    axios(`https://corona.lmao.ninja/v2/countries`).then((data) => {
      setData(data.data);
      console.log(data.data);
    });
  }, []);

  return (
    <>
      <ComposableMap data-tip="" projectionConfig={{ scale: 150 }}>
        <ZoomableGroup>
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
                    fill={d ? colorScale(d["cases"]) : "#F5F4F6"}
                    onClick={null}
                    style={{
                      pressed: {
                        fill: d ? colorScale(d["cases"]) : "#F5F4F6",
                        stroke: "none",
                      },
                    }}
                    onMouseEnter={() => {
                      const { NAME, POP_EST } = geo.properties;
                      setContent(`${NAME} — ${d["cases"]}`);
                    }}
                    onMouseLeave={() => {
                      setContent("");
                    }}
                  />
                );
              })
            }
          </Geographies>
        </ZoomableGroup>
      </ComposableMap>
      <ReactTooltip>{content}</ReactTooltip>
    </>
  );
};

export default memo(WorldMapCases);
