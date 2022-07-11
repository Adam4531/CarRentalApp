import Big from 'big.js';
export interface Car{
    id: number;
    // carName: string;
    brand: string;
    model: string;
    engineCapacity: Big;
    bodyType: string;
    typeOfFuel: string;
    newCarCost: number;
    productionYear: number
}
