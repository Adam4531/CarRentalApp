export class ReservationDto {
    id!: number;
    userId!: number;
    carId!: number;
    dateStart!: Date;
    dateEnd!: Date;
    cost!: number;
    paymentInAdvance!: number;
}
