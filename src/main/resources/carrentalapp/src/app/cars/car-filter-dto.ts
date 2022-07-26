import { BodyType } from "./enums/body-type";
import { TypeOfFuel } from "./enums/type-of-fuel";

export class CarFilterDto {
    brand!: string;
    model!: string;
    engineCapacity!: number;
    bodyType!: BodyType;
    typeOfFuel!: TypeOfFuel;
    productionYear!: number;
    freeFrom!: Date;
    freeTo!: Date;
}
