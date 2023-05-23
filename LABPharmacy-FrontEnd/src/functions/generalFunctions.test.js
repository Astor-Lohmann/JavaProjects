import {
  dateFormatterDDMMAAAA,
  dateFormatterAAAAMMDD,
} from "./generalFunctions";

describe("Testing dateFormatterDDMMAAAA function", () => {
  test('Deve converter a data no formato "AAAA-MM-DDT18:37:31+00:00" para "DD-MM-AAAA"', () => {
    const inputDate = "2023-04-16T18:37:31+00:00";
    const expectedOutputDate = "16-04-2023";
    expect(dateFormatterDDMMAAAA(inputDate)).toEqual(expectedOutputDate);
  });
});

describe("Testing dateFormatterAAAAMMDD function", () => {
  test('Converte a data no formato "DD-MM-AAAA" para o formato "AAAA-MM-DD"', () => {
    const inputDate = "16-04-2023";
    const expectedOutputDate = "2023-04-16";
    expect(dateFormatterAAAAMMDD(inputDate)).toEqual(expectedOutputDate);
  });
});
